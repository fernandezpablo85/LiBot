import com.typesafe.startscript.StartScriptPlugin

seq(StartScriptPlugin.startScriptForClassesSettings: _*)

name := "libot"

version := "1.0"

scalaVersion := "2.8.1"

resolvers += "twitter-repo" at "http://maven.twttr.com"

libraryDependencies ++= Seq("com.twitter" % "finagle-core" % "1.9.0",
                            "com.twitter" % "finagle-http" % "1.9.0",
                            "org.scalatest" %% "scalatest" % "1.5.1" % "test")
