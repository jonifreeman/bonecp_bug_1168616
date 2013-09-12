#!/bin/sh
if test -f ~/.sbtconfig; then
  . ~/.sbtconfig
fi
exec java -Xmx2048M -XX:+CMSClassUnloadingEnabled ${SBT_OPTS} -jar ~/bin/sbt-launch-0.13.jar "$@"
