
def get_recipe(d):
   target_arch = d.getVar('TARGET_ARCH', True)
   if target_arch == "arm":
       if d.getVar('TARGET_FPU', True) in [ 'hard' ]:
           return "zulu-ezdk-armv7hf"
       else:
           return "zulu-ezdk-armv7sf"
   elif target_arch == "aarch64":
       return "zulu-ezdk-aarch64"
   elif target_arch == "x86_64":
       return "zulu-ezdk-x86_64"
   elif target_arch in ["i386", "i486", "i586", "i686"]:
       bb.error("Target architecture '%s' is not supported by the meta-zulu-java layer" %target_arch)
       raise bb.parse.SkipPackage("Target architecture '%s' is not supported by the meta-zulu-java layer" %target_arch)
   else:
       bb.error("Target architecture '%s' is not supported by the meta-zulu-java layer" %target_arch)
       raise bb.parse.SkipPackage("Target architecture '%s' is not supported by the meta-zulu-java layer" %target_arch)

recipe = "${@get_recipe(d)}" 
require ${recipe}.bb

