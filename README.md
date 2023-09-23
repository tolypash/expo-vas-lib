# expo-vas-lib

VAS is a library that enables a third-party application running on a POS terminal to call ArkePay in order to perform a transaction. The third-party application initiates the transaction, control is transferred to ArkePay application, the user performs the transaction (presenting the card, entering PIN where appropriate etc.) and finally the transaction is finalized (Approved or rejected). The transaction result is returned to the calling application and control is transferred back there.

# API documentation

- [Documentation for the main branch](https://github.com/expo/expo/blob/main/docs/pages/versions/unversioned/sdk/vas-lib.md)
- [Documentation for the latest stable release](https://docs.expo.dev/versions/latest/sdk/vas-lib/)

# Installation in managed Expo projects

For [managed](https://docs.expo.dev/versions/latest/introduction/managed-vs-bare/) Expo projects, please follow the installation instructions in the [API documentation for the latest stable release](#api-documentation). If you follow the link and there is no documentation available then this library is not yet usable within managed projects &mdash; it is likely to be included in an upcoming Expo SDK release.

# Installation in bare React Native projects

For bare React Native projects, you must ensure that you have [installed and configured the `expo` package](https://docs.expo.dev/bare/installing-expo-modules/) before continuing.

### Add the package to your npm dependencies

```
npm install expo-vas-lib
```



### Configure for Android



# Contributing

Contributions are very welcome! Please refer to guidelines described in the [contributing guide]( https://github.com/expo/expo#contributing).
