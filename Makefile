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
	javax.json-1.1.3.jar \
	javax.json-api-1.1.3.jar \

JavadocPackages = -subpackages no
JavadocImage    = http://geosoft.no/images/NpdIoBox.250.png

include $(DEV_HOME)/tools/Make/Makefile
