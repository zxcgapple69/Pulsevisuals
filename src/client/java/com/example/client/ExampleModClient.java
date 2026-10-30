name: Build MaxaVisual

on:
  workflow_dispatch:
  push:
    branches: [main]

permissions:
  contents: read

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      - uses: actions/checkout@v4

      - uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: '21'

      - uses: gradle/actions/setup-gradle@v4
        with:
          gradle-version: '8.10'

      - name: Extract source
        run: unzip MaxaVisual.zip

      - name: Build JAR
        run: gradle -p MaxaVisual build --no-daemon

      - uses: actions/upload-artifact@v4
        with:
          name: MaxaVisual-1.21.4
          path: MaxaVisual/build/libs/*.jar
