# XemorConfiguration
My library for handling configs for various common objects in Spigot

## How do you install and use this library?
```bash
In a directory of your pleasing do,
git clone https://github.com/Xemorr/ConfigurationData.git
cd ConfigurationData
mvn clean package install
```

Now, it should be stored in your local maven repository!
You can depend on it in your own plugins like so
```xml
        <dependency>
            <groupId>me.xemor</groupId>
            <artifactId>ConfigurationData</artifactId>
            <version>ENTER VERSION HERE</version>
            <scope>provided</scope>
        </dependency>
```

## How do you update the library?
Navigate back to where you initially installed the library (if you have forgotten, follow initial installation again)
Now execute 
```git
git pull
```
