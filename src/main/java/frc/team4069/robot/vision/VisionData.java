package frc.team4069.robot.vision;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.VisionThread;
import frc.team4069.robot.vision.TapeTrackingPipeline.Line;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

// The static class that contains the vision threads and the data updated by them
public class VisionData {

    // The number of lines that will be found and averaged
    private static final int numLines = 4;

    // The X and Y positions of the center of the power cube found on screen
    public static int powerCubeXPos;
    public static int powerCubeYPos;
    // The average X position of the lines found in the captured image
    public static double averageLineX;
    // Vision threads for both cameras
    private static VisionThread armCameraVisionThread;
    private static VisionThread frontCameraVisionThread;

    // Set up the vision threads and set them to run as the camera captures frames
    public static void configureVision() {

        // Create the first and second cameras, which are located on the lifting arm and at the
        // front of the robot, respectively
        CameraServer server = CameraServer.getInstance();
        UsbCamera armCamera = server.startAutomaticCapture(0);
        armCamera.setResolution(320, 240);
        UsbCamera frontCamera = server.startAutomaticCapture(1);
        frontCamera.setResolution(320, 240);

        // Initialize the arm camera thread and update the position of the power cube every frame
        armCameraVisionThread = new VisionThread(armCamera, new ArmCameraPipeline(),
                pipeline -> {
                    // If an image is in the field
                    if (!pipeline.findContoursOutput().isEmpty()) {
                        // Get the maximum extents of the contours
                        Rect contourBounds = Imgproc
                                .boundingRect(pipeline.findContoursOutput().get(0));
                        // Set the public values accordingly
                        powerCubeXPos = contourBounds.x + (contourBounds.width / 2);
                        powerCubeYPos = contourBounds.x + (contourBounds.height / 2);
                    }
                }
        );

        // Initialize the front camera thread, updating the average position of the lines each frame
        frontCameraVisionThread = new VisionThread(frontCamera, new TapeTrackingPipeline(),
                pipeline -> {
                    ArrayList<Line> lines = pipeline.filterLinesOutput();
                    // If there are at least four lines
                    if (pipeline.filterLinesOutput().size() >= numLines) {
                        // Sort the lines
                        Collections.sort(lines, new LineComparator());
                        // Get the first four elements of the list
                        List<Line> firstLines = lines.subList(0, numLines);
                        // Get the average X position of the lines
                        double lineXAccumulator = 0;
                        for (int i = 0; i < numLines; i++) {
                            Line line = firstLines.get(i);
                            lineXAccumulator += (line.x1 + line.x2);
                        }
                        averageLineX = lineXAccumulator / (numLines * 2);
                    }
                }
        );

        // Run the threads
        armCameraVisionThread.start();
    }

    // Comparator that allows comparing the lengths of Line objects
    private static class LineComparator implements Comparator<Line> {

        @Override
        public int compare(Line line0, Line line1) {
            return lineLength(line0).compareTo(lineLength(line1));
        }

        private Double lineLength(Line line) {
            return Math.sqrt(Math.pow(line.x2 - line.x1, 2) + Math.pow(line.y2 - line.y1, 2));
        }
    }
}
