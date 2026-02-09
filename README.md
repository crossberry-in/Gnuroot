# genroot

**genroot** is a powerful terminal emulator providing seamless access to Alpine Linux and Ubuntu GNU/Linux environments in a sandboxed setup with sudo access (no password required). Built on [Termux's](https://github.com/termux/termux-app) robust TerminalView with a modern Material 3-inspired interface.

Created by [crossberry](https://crossberry.vercel.app). Visit our site for more info and updates.

Download the latest APK from the [Releases Section](https://github.com/RohitKushvaha01/ReTerminal/releases/latest).

# Features
- [x] Full Linux terminal emulation with bash/zsh
- [x] Customizable virtual keys
- [x] Multiple session tabs
- [x] Alpine Linux & Ubuntu support
- [x] Sandboxed Linux environment
- [x] Sudo access without password (in sandbox)
- [x] Material 3 design

# Screenshots
<div>
  <img src="/fastlane/metadata/android/en-US/images/phoneScreenshots/01.png" width="32%" />
  <img src="/fastlane/metadata/android/en-US/images/phoneScreenshots/02.jpg" width="32%" />
  <img src="/fastlane/metadata/android/en-US/images/phoneScreenshots/03.jpg" width="32%" />
</div>

## Community
> [!TIP]
Join the reTerminal community to stay updated and engage with other users:
- [Telegram](https://t.me/reTerminal)


# FAQ

### **Q: Why do I get a "Permission Denied" error when trying to execute a binary or script?**
**A:** This happens because genroot runs on the latest Android API, which enforces **W^X restrictions**. Since files in `$PREFIX` or regular storage directories can't be executed directly, you need to use one of the following workarounds:

---

### **Option 1: Use the Dynamic Linker (for Binaries)**
If you're trying to run a binary (not a script), you can use the dynamic linker to execute it:

```bash
$LINKER /absolute/path/to/binary
```

✅ **Note:** This method won't work for **statically linked binaries** (binaries without external dependencies).

---

### **Option 2: Use `sh` for Scripts**
If you're trying to execute a shell script, simply use `sh` to run it:

```bash
sh /path/to/script
```

This bypasses the need for execute permissions since the script is interpreted by the shell.

---

### **Option 3: Use Shizuku for Full Shell Access (Recommended)**
If you have **Shizuku** installed, you can gain shell access to `/data/local/tmp`, which has executable permissions. This is the easiest way to run binaries without restrictions.


## Found this app useful? :heart:
Support it by giving a star :star: <br>
Also, **__[follow](https://github.com/Rohitkushvaha01)__** me for my next creations!

