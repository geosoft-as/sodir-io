ProjectDisplayName = Sodir

JavaPackages = \
        no/geosoft/sodir \
        no/geosoft/sodir/company \
        no/geosoft/sodir/discovery \
        no/geosoft/sodir/facility \
        no/geosoft/sodir/field \
        no/geosoft/sodir/json \
        no/geosoft/sodir/license \
        no/geosoft/sodir/pipeline \
        no/geosoft/sodir/stratigraphy \
        no/geosoft/sodir/survey \
        no/geosoft/sodir/web \
        no/geosoft/sodir/well \

JavaLibraries = \
	jakarta.servlet-api-5.0.0.jar \
	javax.json-1.1.3.jar \
	javax.json-api-1.1.3.jar \
	jetty-http-11.0.24.jar \
	jetty-io-11.0.24.jar \
	jetty-security-11.0.24.jar \
	jetty-server-11.0.24.jar \
	jetty-servlet-11.0.24.jar \
	jetty-util-11.0.24.jar \
	slf4j-api-2.0.16.jar \
	slf4j-jdk14-2.0.16.jar \

JavadocPackages = -subpackages no
JavadocImage    = http://geosoft.no/images/NpdIoBox.250.png

include $(DEV_HOME)/tools/Make/Makefile
