#!/usr/bin/python

import sys
import os
import subprocess
import shutil
import re
import base64
import string
import argparse


def print_usage():
    print("ostree_image.py --image image.tar.gz --repo ./repo --tree ./rootfstree --deltasdir ./deltas --packagename pack --message \"Commit message\"")

def find_last_delta(path, prefix):
    listing = os.listdir(path)

    maxdelta=-1
    fr = ""
    to = ""

    regex = re.compile("^" + prefix + "-delta(\d+)-([^\-]+)-([^\-\.]+)\.tar.gz$")
    for cand in listing:
        m = regex.match(cand)
        if(m):
            if(int(m.group(1)) > maxdelta):
                maxdelta = int(m.group(1))
                fr = m.group(2)
                to = m.group(3)

    return (maxdelta, fr, to)

def hexstring_to_binstring(hexst):
    ret = "" 
    while(hexst):
        num = hexst[:2]
        hexst = hexst[2:]
	ret += chr(int(num, 16))
    return ret

def delta_folder_name(from_sha, to_sha):
    from_bin = hexstring_to_binstring(from_sha)
    to_bin = hexstring_to_binstring(to_sha)

    from_b64 = base64.urlsafe_b64encode(from_bin).rstrip("=")
    to_b64 = base64.urlsafe_b64encode(to_bin).rstrip("=")

    if(from_b64):
        namestr = from_b64 + "-" + to_b64
    else:
        namestr = to_b64

    return namestr[:2] + "/" + namestr[2:]
    
def main(argc, argv):
    argc -= 1
    argi = 1

    parser = argparse.ArgumentParser()
    parser.add_argument('-i', '--image', required=True, help="Rootfs image tarball")
    parser.add_argument('-r', '--repo', required=True, help="Path to OSTree repository")
    parser.add_argument('-t', '--tree', required=True, help="Where to unpack the tarball")
    parser.add_argument('-d', '--deltasdir', required=True, help="Where delta tarballs should be stored")
    parser.add_argument('-p', '--packagename', required=True, help="Package name that serves as a branch name for OSTree")
    parser.add_argument('-m', '--message', required=True, help="Commit message")
    args = parser.parse_args()
    
    if(os.path.exists(args.tree)):
        shutil.rmtree(args.tree)
    os.makedirs(args.tree)

    subprocess.check_call(["tar", "xf", args.image, "-C", args.tree])

    if(not os.path.exists(args.repo)):
	os.makedirs(args.repo)
        subprocess.check_call(["ostree", "--repo="+args.repo, "init", "--mode=archive-z2"])

    if(not os.path.exists(args.deltasdir)):
	os.makedirs(args.deltasdir)

    deltaprefix = args.packagename.translate(string.maketrans('/\\', '__'))

    delta_num, delta_from, delta_to = find_last_delta(args.deltasdir, deltaprefix)

    # commit    
    to = subprocess.check_output(["ostree", "--repo="+args.repo, "commit", '--subject=\"'+args.message+"\"", '--branch='+args.packagename, "--tree=dir="+args.tree]).strip() 

    deltafolder = delta_folder_name("" if (delta_num < 0) else delta_to, to)

    # generate static delta
    if(delta_num < 0):
        subprocess.check_call(["ostree", "--repo="+args.repo, "static-delta", "generate", '--empty', '--to='+to, "--inline", "--min-fallback-size=65536"]) 
        subprocess.check_call(["tar", "czf", args.deltasdir+"/"+deltaprefix+"-delta0-"+"empty-"+to+".tar.gz", "-C", args.repo+"/deltas", args.repo+"/deltas/"+deltafolder])
    else:
        subprocess.check_call(["ostree", "--repo="+args.repo, "static-delta", "generate", '--from='+delta_to, '--to='+to, "--inline", "--min-fallback-size=65536"]) 
        subprocess.check_call(["tar", "czf", args.deltasdir+"/"+deltaprefix+"-delta"+str(delta_num+1)+"-"+delta_to+"-"+to+".tar.gz", "-C", args.repo+"/deltas", args.repo+"/deltas/"+deltafolder])


if __name__ == "__main__":
    main(len(sys.argv), sys.argv)        	

