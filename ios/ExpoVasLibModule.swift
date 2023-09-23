import ExpoModulesCore

public class ExpoVasLibModule: Module {
  // Each module class must implement the definition function. The definition consists of components
  // that describes the module's functionality and behavior.
  // See https://docs.expo.dev/modules/module-api for more details about available components.
  public func definition() -> ModuleDefinition {
    // Sets the name of the module that JavaScript code will use to refer to the module. Takes a string as an argument.
    // Can be inferred from module's class name, but it's recommended to set it explicitly for clarity.
    // The module will be accessible from `requireNativeModule('ExpoVasLib')` in JavaScript.
    Name("ExpoVasLib")

    // Defines event names that the module can send to JavaScript.
    Events("onResponseReceived")

    // Defines a JavaScript synchronous function that runs the native code on the JavaScript thread.
    Function("initialise") {
      return "Not implemented"
    }

    // Defines a JavaScript function that always returns a Promise and whose native code
    // is by default dispatched on the different thread than the JavaScript runtime runs on.
    AsyncFunction("doTransaction") {
      return "Not implemented"
    }
  }
}
