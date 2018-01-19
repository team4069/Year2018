package frc.team4069.robot.vision;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.VisionThread;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

// The static class that contains the vision threads and the data updated by them
public class VisionData {

    // The X and Y positions of the center of the power cube found on screen
    public static int powerCubeXPos;
    public static int powerCubeYPos;
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
        armCameraVisionThread = new VisionThread(armCamera, new ArmCameraPipeline(), pipeline -> {
            // If an image is in the field
            if (!pipeline.findContoursOutput().isEmpty()) {
                // Get the maximum extents of the contours
                Rect contourBounds = Imgproc.boundingRect(pipeline.findContoursOutput().get(0));
                // Set the public values accordingly
                powerCubeXPos = contourBounds.x + (contourBounds.width / 2);
                powerCubeYPos = contourBounds.x + (contourBounds.height / 2);
            }
        });
        // Run the thread
        armCameraVisionThread.start();
    }
}
