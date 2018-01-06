#!/bin/bash

bail() {
    echo "$1"
    exit 1;
}

networkAvailable() {
    nmcli c show | awk '{print $1}' | while read a b; do
        if [[ "${a}" == "Student" ]]; then
            echo "TRUE";
            return 0;
        fi
    done
}

nmcli -v > /dev/null 2>&1 || bail "nmcli not found";

if [[ $(networkAvailable) == "TRUE" ]]; then
    nmcli con up id "Student"
fi
