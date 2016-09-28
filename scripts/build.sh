#!/bin/bash

# To build with rust 1.10 takes a two step proceess, the first to do the cargo
# fetch, the second to build the externalsrc sota-client code. This script
# uncomments and comments the appropriate lines to make that happen. When the
# issue https://github.com/meta-rust/meta-rust/issues/84 is resolved, we can
# get rid of this script.

bitbake -k openivi-image
sed -i '/systemd\ externalsrc/s/^#//g' ../meta-oim/recipes-oim/sota-client/sota-client.bb
bitbake -k openivi-image
sed -i '/systemd\ externalsrc/s/^/#/g' ../meta-oim/recipes-oim/sota-client/sota-client.bb
