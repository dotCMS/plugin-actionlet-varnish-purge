
README
------

This bundle plugin will provide a workflow actionlet called "Purge Varnish Cache" that can be added to any workflow process.  It is a proof oc concept plugin that allows you to create workflows that will purge an external varnish cache You configure your varnish endpoint using the plugin.properties file here:
`/src/main/resources/plugin.properties`




How to build this plugin
-------------------------

To install all you need to do is build the JAR. to do this run 
./gradlew jar
This will build a jar in the build/libs directory

1. To install this bundle:

Copy the bundle jar file inside the Felix OSGI container (dotCMS/felix/load).
        OR
Upload the bundle jar file using the dotCMS UI (CMS Admin->Dynamic Plugins->Upload Plugin).
	
2. To uninstall this bundle:

Remove the bundle jar file from the Felix OSGI container (dotCMS/felix/load).
        OR
Undeploy the bundle using the dotCMS UI (CMS Admin->Dynamic Plugins->Undeploy).

# plugin-actionlet-varnish-purge
