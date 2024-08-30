# sodir - Sokkeldirektoratet access library

The sodir module is a library for reading information from the
Sokkeldirektoratet (Sodir) (= Norwegian Offshore Directorate) public fact pages.


### Setup

Capture the Sodir code to local disk by:

```
$ git clone git@github.com:geosoft-as/sodir.git
```



### Dependencies

Sodir has no external dependencies.



### Building Sodir

Sodir can be built from its root folder by

```
$ make clean
$ make
$ make jar
```

The Sodir delivery will be the `./lib/sodir.jar` file.

Building with make require the make module containing the main Makefile.



