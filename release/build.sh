#!/bin/zsh -eu

version="${1:-}"

if [ -z "$version" ]; then
  echo "usage: $0 <version>" >&2
  exit 1
fi

ant clean
ant -DRELEASE=$version -Ddebug=true

mvn install:install-file \
	-Dfile=dist/VAppearances.jar \
	-DgroupId=org.violetlib \
	-DartifactId=vappearances \
	-Dversion=$version \
	-Dpackaging=jar

(cd ../src && jar cf ../release/vappearances-$version-sources.jar .)
mv vappearances-$version-sources.jar ~/.m2/repository/org/violetlib/vappearances/$version/
