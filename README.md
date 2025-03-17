# XemorConfiguration
My library for handling configs for various common objects in Spigot

## How do you install and use this library?
You can install it into your maven cache by doing
```
In a directory of your pleasing do,
git clone https://github.com/Xemorr/ConfigurationData.git
cd ConfigurationData
mvn clean package install
```


You can depend on it in your own plugins like so
You should make sure that it's sub dependencies i.e jackson and minimessage are relocated correctly.
```xml
        <dependency>
            <groupId>me.xemor</groupId>
            <artifactId>ConfigurationData</artifactId>
            <version>ENTER VERSION HERE</version>
            <scope>provided</scope>
        </dependency>
```
