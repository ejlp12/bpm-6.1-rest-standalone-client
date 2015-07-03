
A sample of standalone client using the class `RemoteRuntimeEngineFactory` to access BPM tasks.
 
To execute the client, you must have Maven installed and make sure you have following repository in your `~/.m2/setting.xml`:

```
            <repositories>
                <repository>
                    <id>jboss-ga-repository</id>
                    <name>JBoss GA Tech Preview Maven Repository</name>
                    <url>http://maven.repository.redhat.com/techpreview/all</url>
                    <layout>default</layout>
                    <releases>
                        <enabled>true</enabled>
                        <updatePolicy>never</updatePolicy>
                    </releases>
                    <snapshots>
                        <enabled>false</enabled>
                        <updatePolicy>never</updatePolicy>
                    </snapshots>
                </repository>
            </repositories>
            <pluginRepositories>
                <pluginRepository>
                    <id>jboss-ga-plugin-repository</id>
                    <name>JBoss 6 Maven Plugin Repository</name>
                    <url>http://maven.repository.redhat.com/techpreview/all</url>
                    <layout>default</layout>
                    <releases>
                        <enabled>true</enabled>
                        <updatePolicy>never</updatePolicy>
                    </releases>
                    <snapshots>
                        <enabled>false</enabled>
                        <updatePolicy>never</updatePolicy>
                    </snapshots>
                </pluginRepository>
            </pluginRepositories>
```

Then you just need to type the following command:

$ mvn package exec:java -Dexec.mainClass="ejlp.sample.Main"



