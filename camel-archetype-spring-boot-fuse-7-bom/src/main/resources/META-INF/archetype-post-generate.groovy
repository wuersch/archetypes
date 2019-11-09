// This file is automatically executed after the archetype has applied all
// templates and finished generating its artifacts. Used to delete files
// that are only relevant to certain profiles, because archetypes are not
// able to conditionally include or exclude files (all are copied instead).

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

// The path where the project got generated
Path projectPath = Paths.get(request.outputDirectory, request.artifactId)

// The properties available to the archetype
Properties properties = request.properties

// Property cxfSupport is either true or false
boolean cxfSupport = properties.get("cxfSupport").toBoolean()

// Java package name of the generated project
String packageName = properties.get("package")

// Convert into a path
String packagePath = packageName.replace(".", "/")

if (cxfSupport != true) {
  // Delete the WSDL and its parent folder
  Files.deleteIfExists projectPath.resolve("src/main/resources/wsdl/contract.wsdl")
  Files.deleteIfExists projectPath.resolve("src/main/resources/wsdl")

  // Delete the policy and security properties file
  Files.deleteIfExists projectPath.resolve("src/main/resources/ws-policy.xml")
  Files.deleteIfExists projectPath.resolve("src/main/resources/security.properties")

  // Delete SAML Callback
  Files.deleteIfExists projectPath.resolve("src/main/java/${packagePath}/CustomSAMLCallback.java")
}