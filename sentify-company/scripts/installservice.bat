set JETTY_HOME=D:\Devel\Programs\jetty-hightide-8.1.5.v20120716

bin\JettyService //IS//JettyService --DisplayName="Jetty Service" --Install=%JETTY_HOME%\bin\JettyService.exe --LogPath=%JETTY_HOME%\logs --LogLevel=Debug --StdOutput=auto --StdError=auto --StartMode=Java --StopMode=Java --Jvm=auto ++JvmOptions=-Djetty.home=%JETTY_HOME% ++JvmOptions=-DSTOP.PORT=8087 ++JvmOptions=-DSTOP.KEY=downB0y ++JvmOptions=-Djetty.logs=%JETTY_HOME%\logs ++JvmOptions=-Dorg.eclipse.jetty.util.log.SOURCE=true ++JvmOptions=-XX:MaxPermSize=128M ++JvmOptions=-XX:+CMSClassUnloadingEnabled ++JvmOptions=-XX:+CMSPermGenSweepingEnabled --Classpath=%JETTY_HOME%\start.jar --StartClass=org.eclipse.jetty.start.Main ++StartParams=OPTIONS=All ++StartParams=%JETTY_HOME%\etc\jetty.xml ++StartParams=%JETTY_HOME%\etc\jetty-deploy.xml ++StartParams=%JETTY_HOME%\etc\jetty-webapps.xml ++StartParams=%JETTY_HOME%\etc\jetty-contexts.xml ++StartParams=%JETTY_HOME%\etc\jetty-testrealm.xml --StopClass=org.eclipse.jetty.start.Main ++StopParams=--stop
