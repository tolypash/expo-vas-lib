const { withProjectBuildGradle } = require("expo/config-plugins");

/**
 * 
 * @param {import("@expo/config-types").ExpoConfig} config 
 * @returns 
 */
const withVasLibGradle = (config) => {
  return withProjectBuildGradle(config, (config) => {
    config.modResults.contents = config.modResults.contents.replace(
      "maven { url 'https://www.jitpack.io' }",
      `maven { url 'https://jitpack.io' }\n        flatDir { dirs "$rootDir/../node_modules/expo-vas-lib/android/libs" }`
    );

    return config;
  });
};

module.exports = withVasLibGradle;
