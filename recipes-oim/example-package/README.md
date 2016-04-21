# Example package for installing in demo

Build with bitbake example-package.

To build the two different versions, add this to your conf/local.conf:

```
PREFERRED_VERSION_example-package = "1.0.0"
```

And switch between `1.0.0` and `2.0.0`.

You'll find the rpm at something like `build/tmp/work/core2-64-poky-linux/example-package/1.0.0-r0/deploy-rpms/core2_64/example-package-1.0.0-r0.core2_64.rpm`

