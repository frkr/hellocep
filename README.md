# Lab Piloto MVC JAVA

### Usando e iniciando projeto:

* Usei o JBoss *"wildfly-8.2.1.Final""* para testes.
* Após um clássico *"mvn clean install package"*
* Faça o deploy
* O **EAR** estará na pasta **hellocep-ear/target/hellocep.ear**
* Ou o comando **deploy** costuma funcionar
```sh
mvn jboss-as:deploy
```

[http://localhost:8080/hellocep-web](http://localhost:8080/hellocep-web)

***
### Criando o projeto do zero a partir do esqueleto usando arquétipos:
* O primeiro comando irá criar o diretório **"hellocep"**
* Seguido pela estrutura de projetos EE

```sh
mvn archetype:generate -DarchetypeGroupId=org.codehaus.mojo.archetypes -DarchetypeArtifactId=pom-root -DgroupId=com.github.eepiloto -DartifactId=hellocep -Darchetype.interactive=false --update-snapshots -Dversion=1.0-SNAPSHOT
cd hellocep
mvn archetype:generate -DarchetypeGroupId=org.codehaus.mojo.archetypes -DarchetypeArtifactId=ejb-javaee7 -DgroupId=com.github.eepiloto -DartifactId=hellocep-ejb -Darchetype.interactive=false --update-snapshots -Dversion=1.0-SNAPSHOT
mvn archetype:generate -DarchetypeGroupId=org.codehaus.mojo.archetypes -DarchetypeArtifactId=webapp-javaee7 -DgroupId=com.github.eepiloto -DartifactId=hellocep-web -Darchetype.interactive=false --update-snapshots -Dversion=1.0-SNAPSHOT
mvn archetype:generate -DarchetypeGroupId=org.codehaus.mojo.archetypes -DarchetypeArtifactId=ear-javaee7 -DgroupId=com.github.eepiloto -DartifactId=hellocep-ear -Darchetype.interactive=false --update-snapshots -Dversion=1.0-SNAPSHOT
```

### Estrutura de Projetos
* ejb: Business, Model e Database
* web: View e Serviços Externos HTTP

### Estrutura de pacotes
* Base: com.github.eepiloto
* ext: Serviços Externos
* ext.rest: RESTFul
* view: View
* dto: Data Transfer Object: Auxiliarores de serviços e View
* dao: Model: Data Access Object
* database: Database: Apenas entity de banco de dados
* service: Business: Servicos e EJB

**REST:** Não é registrado automáticamente
* [Deployment Servlet 3.x No APP](https://jersey.java.net/documentation/latest/deployment.html#deployment.servlet.3.pluggability.noapp)

Adicionando dependencias de REST:
```sh
hellocep\hellocep-web\pom.xml
```
```xml
        <dependency>
            <groupId>com.fasterxml.jackson.jaxrs</groupId>
            <artifactId>jackson-jaxrs-json-provider</artifactId>
            <version>2.5.4</version>
        </dependency>
```
```sh
hellocep\hellocep-web\src\main\webapp\WEB-INF\web.xml
```
```xml
<web-app version="3.0"
         xmlns="http://java.sun.com/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">

    <!-- Servlet declaration can be omitted in which case
         it would be automatically added by Jersey -->
    <servlet>
        <servlet-name>javax.ws.rs.core.Application</servlet-name>
    </servlet>

    <servlet-mapping>
        <servlet-name>javax.ws.rs.core.Application</servlet-name>
        <url-pattern>/rest/*</url-pattern>
    </servlet-mapping>
</web-app>
```
```sh
hellocep\hellocep\pom.xml
```

PrimeFaces:
```sh
hellocep\hellocep-web\src\main\webapp\WEB-INF\web.xml
```
```sh
hellocep\hellocep-web\pom.xml
```
```
        <dependency>
            <groupId>org.primefaces</groupId>
            <artifactId>primefaces</artifactId>
            <version>5.2</version>
        </dependency>
```
```sh
hellocep-web\src\main\webapp\WEB-INF\faces-config.xml
```
```xml
<?xml version='1.0' encoding='UTF-8'?>
<faces-config version="2.2"
              xmlns="http://xmlns.jcp.org/xml/ns/javaee"
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-facesconfig_2_2.xsd">

    <application>
        <action-listener>org.primefaces.application.DialogActionListener</action-listener>
        <navigation-handler>org.primefaces.application.DialogNavigationHandler</navigation-handler>
        <view-handler>org.primefaces.application.DialogViewHandler</view-handler>
        <navigation-handler>org.primefaces.mobile.application.MobileNavigationHandler</navigation-handler>

        <el-resolver>org.primefaces.application.exceptionhandler.PrimeExceptionHandlerELResolver</el-resolver>
        <resource-bundle>
            <base-name>build</base-name>
            <var>build</var>
        </resource-bundle>
    </application>

    <factory>
        <exception-handler-factory>org.primefaces.application.exceptionhandler.PrimeExceptionHandlerFactory</exception-handler-factory>
    </factory>

</faces-config>
```
JPA EJB:
* Estou mapeando classe a classe
```sh
hellocep-ejb\src\main\resources\META-INF\persistence.xml
```
```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://java.sun.com/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd" version="2.0">
    <persistence-unit name="eepilotoPU" transaction-type="RESOURCE_LOCAL">
        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
        <class>com.github.eepiloto.database.CEP</class>
        <class>com.github.eepiloto.database.Endereco</class>
        <properties>
            <property name="hibernate.dialect" value="org.hibernate.dialect.HSQLDialect"/>
            <property name="javax.persistence.jdbc.url" value="jdbc:hsqldb:mem:testdb"/>
            <property name="hibernate.connection.driver_class" value="org.hsqldb.jdbcDriver"/>
            <property name="hibernate.connection.password" value=""/>
            <property name="hibernate.connection.username" value="sa"/>
            <property name="hibernate.connection.provider.class" value="org.hibernate.connection.DriverManagerConnectionProvider"/>
            <property name="hibernate.cache.provider_class" value="org.hibernate.cache.NoCacheProvider"/>
            <property name="hibernate.hbm2ddl.auto" value="create-drop"/>
            <property name="hibernate.show_sql" value="true" />
        </properties>
    </persistence-unit>
</persistence>
```
```sh
hellocep-ejb\pom.xml
```
```xml
        <dependency>
            <groupId>org.hsqldb</groupId>
            <artifactId>hsqldb</artifactId>
            <version>2.3.3</version>
        </dependency>
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-entitymanager</artifactId>
            <version>5.0.0.Final</version>
        </dependency>
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-validator</artifactId>
            <version>5.2.1.Final</version>
        </dependency>
```

Testes e documentação dos cenários:
* Integração com o "IntelliJ IDEA", definir o diretório de testes
```sh
pom.xml
```
```xml
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.jglue.cdi-unit</groupId>
            <artifactId>cdi-unit</artifactId>
            <version>3.1.2</version>
            <scope>test</scope>
        </dependency>

    <build>
        <testSourceDirectory>src/test</testSourceDirectory>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-javadoc-plugin</artifactId>
                <version>2.10.3</version>
                <executions>
                    <execution>
                        <phase>test</phase>
                        <goals><goal>test-javadoc</goal></goals>
                    </execution>
                </executions>
            </plugin>
```

[http://localhost:8080/hellocep-web](http://localhost:8080/hellocep-web)
