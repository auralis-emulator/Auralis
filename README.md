# Auralis Emulator

**Auralis** is a modern, open-source Nintendo 3DS emulator focused on
accuracy, performance, and long-term maintainability.

Auralis runs on **Windows, Linux, macOS, and Android**, and is designed to
provide a clean, stable, and future-proof emulation experience.

🌐 Website: https://auralis-emulator.com

---
## ✨ Features

- High-accuracy Nintendo 3DS emulation
- Hardware-accelerated rendering
- Save states and game backups
- Customizable controls and layouts
- Cross-platform support (Desktop & Android)
- Actively maintained open-source codebase

---
## 🖥️ Supported Platforms

- Windows (x64)
- Linux
- macOS
- Android

---
## 📦 Downloads

Prebuilt binaries are available from:

- GitHub Releases
- https://auralis-emulator.com

---
## 🛠️ Building from Source

### Desktop (Windows / Linux / macOS)
```bash
mkdir build
cd build
cmake .. -DCMAKE_BUILD_TYPE=Release
cmake --build .
```

### Android
```bash
cd src/android
./gradlew assembleRelease
```

---
## 🧬 Project Lineage

Auralis is derived from the open-source **Auralis** project, which itself
originated as a fork of **Citra**.

All upstream licenses and third-party attributions are preserved in
accordance with the GNU General Public License.

---
## 📄 License

Auralis is licensed under the **GNU General Public License v2**.
See the `license.txt` file for details.
