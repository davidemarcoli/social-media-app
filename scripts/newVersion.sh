# directory of the script
dirname=$( dirname -- "$0"; )

# go to the root directory of the project
cd "$dirname/.." || exit 1

# Change the maven version
mvn versions:set -DnewVersion="$1"
mvn versions:commit

# Change the version in the frontend
sed -i "s/\"version\": \".*\"/\"version\": \"$1\"/" frontend/package.json