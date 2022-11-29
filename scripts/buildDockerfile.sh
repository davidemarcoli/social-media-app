# Test if the script is running as root
if [ "$(id -u)" != "0" ]; then
    echo "This script must be run as root" 1>&2
    exit 1
fi

# directory of the script
dirname=$( dirname -- "$0"; )

# go to the root directory of the project
cd "$dirname/.." || exit 1

# build the docker image
mvn clean package -DskipTests
sudo docker build -t project-template .