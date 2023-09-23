# expo-vas-lib

VAS is a library that enables a third-party application running on a POS terminal to call ArkePay in order to perform a transaction. The third-party application initiates the transaction, control is transferred to ArkePay application, the user performs the transaction (presenting the card, entering PIN where appropriate etc.) and finally the transaction is finalized (Approved or rejected). The transaction result is returned to the calling application and control is transferred back there.

# Installation in managed Expo projects

For [managed](https://docs.expo.dev/versions/latest/introduction/managed-vs-bare/) Expo projects, please follow the installation instructions below.

# Installation in bare React Native projects

For bare React Native projects, you must ensure that you have [installed and configured the `expo` package](https://docs.expo.dev/bare/installing-expo-modules/) before continuing.

### Add the package to your npm dependencies

```
npm install expo-vas-lib
```



### Configure for Android



# Contributing

Contributions are very welcome! Please refer to guidelines described in the [contributing guide]( https://github.com/expo/expo#contributing).
