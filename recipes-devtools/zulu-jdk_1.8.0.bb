
def get_recipe(d):
   target_arch = d.getVar('TARGET_ARCH', True)
   if target_arch == "arm":
       if d.getVar('TARGET_FPU', True) in [ 'hard' ]:
           return "zulu-ezdk-arm-hflt"
       else:
           return "zulu-ezdk-arm-sflt"
   elif target_arch in ["i386", "i486", "i586", "i686"]:
       return "zulu-ezdk-x86-64"
   elif target_arch == "x86_64":
       return "zulu-ezdk-x86-64"
   else:
       raise bb.parse.SkipPackage("Target architecture '%s' is not supported by the meta-azul-java layer" %target_arch)
       return ""

RECIPE = "${@get_recipe(d)}" 
require ${RECIPE}.bb

