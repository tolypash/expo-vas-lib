import { useState } from "react";
import { Button, ScrollView, StyleSheet, Text, View } from "react-native";
import * as ExpoVasLib from "expo-vas-lib";
import JSONTree from "react-native-json-tree";

export default function App() {
  const [responseJSON, setResponseJSON] = useState<ExpoVasLib.ResponseData>();

  return (
    <View style={styles.container}>
      <Text>Expo VAS Library</Text>

      <Button
        onPress={() => {
          ExpoVasLib.initialise();
        }}
        title="Initialise"
      />

      <Button
        onPress={async () => {
          const res = await ExpoVasLib.doTransaction(5.21, "123456", true);

          console.log(res);

          setResponseJSON(res);
        }}
        title="Do transaction"
      />

      <ScrollView style={{ height: 100 }}>
        <JSONTree data={responseJSON || {}} />
      </ScrollView>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
    gap: 20,
  },
});
