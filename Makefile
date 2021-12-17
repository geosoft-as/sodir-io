ProjectDisplayName = NPD I/O

JavaPackages = \
        no/geosoft/npdio \
        no/geosoft/npdio/company \
        no/geosoft/npdio/discovery \
        no/geosoft/npdio/facility \
        no/geosoft/npdio/field \
        no/geosoft/npdio/license \
        no/geosoft/npdio/pipeline \
        no/geosoft/npdio/stratigraphy \
	no/geosoft/npdio/survey \
	no/geosoft/npdio/well \

JavadocPackages = -subpackages no
JavadocImage    = http://geosoft.no/images/NpdIoBox.250.png

include $(DEV_HOME)/tools/Make/Makefile
