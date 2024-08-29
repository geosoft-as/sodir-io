ProjectDisplayName = Sodir

JavaPackages = \
        no/geosoft/sodir \
        no/geosoft/sodir/company \
        no/geosoft/sodir/discovery \
        no/geosoft/sodir/facility \
        no/geosoft/sodir/field \
        no/geosoft/sodir/license \
        no/geosoft/sodir/pipeline \
        no/geosoft/sodir/stratigraphy \
        no/geosoft/sodir/survey \
        no/geosoft/sodir/well \

JavadocPackages = -subpackages no
JavadocImage    = http://geosoft.no/images/NpdIoBox.250.png

include $(DEV_HOME)/tools/Make/Makefile
