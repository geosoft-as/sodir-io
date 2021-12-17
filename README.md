# NPD I/O - NPD access library #

The NPD I/O module is a library for reading information from the
Norwegian Petroleum Directorate (NPD) public fact pages.

![JWitsml library](https://geosoft.no/images/NpdIoBox.250.png)


### Setup ###

Capture the NPD I/O code to local disk by:

```
$ git clone <token>@github.com/rabbagast/NpdIo --branch <branch-name>
```

After the initial clone, the directory must be renamed from npdio to NpdIo
in order for the make based tools to work.


### Dependencies ###

NPD I/O has no external dependencies.



### Building NPD I/O ###

NPD I/O can be built from its root folder by

```
$ make clean
$ make
$ make jar
```

The NPD I/O delivery will be the `./lib/NpdIo.jar` file.

Building with make require the make module containing the main Makefile.


### Creating API documentation ###

Javadoc can be created by:

```
$ make doc
$ make brand
$ make analytics
```

The command wil populate the `./docs` tree, entry point will be `./docs/index.html`.

Note the `./overview.html` page that becomes part of the Javadoc.

The _brand_ step will give GeoSoft coloring and embed the GeoSoft logo and
cutsom image while the _analytics_ step will embed Google analytics code for
tracking.

Copy the documentation to the web repository and publish:

```
$ rm -R $DEV_HOME/geosoft.no/npdio/javadoc
$ cp -R docs $DEV_HOME/geosoft.no/npdio/javadoc
$ cd $DEV_HOME/geosoft.no/npdio
$ find javadoc -type f -exec curl --ftp-create-dirs -u geosoft.no:<password> -T {} ftp://ftp.geosoft.no/npdio/javadoc/{} \;
```


