#!/system/bin/sh

# Ubuntu init script (runs inside proot)

# Ensure we have essential directories
mkdir -p /dev/shm
mkdir -p /tmp

# Set up environment
export HOME=/root
export PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin
export PS1="\[\e[38;5;46m\]\u@genroot \[\033[39m\]\w \[\033[0m\]\\$ "
export LANG=C.UTF-8
export TERM=xterm-256color

# Configure DNS if resolv.conf is missing or empty
if [ ! -s /etc/resolv.conf ]; then
    echo "Setting up DNS..."
    printf 'nameserver 8.8.8.8\nnameserver 8.8.4.4\n' > /etc/resolv.conf
fi

# Configure APT to use port.ubuntu.com mirror
if [ -f /etc/apt/sources.list ]; then
    sed -i 's|http://archive.ubuntu.com/ubuntu|http://port.ubuntu.com|g' /etc/apt/sources.list
    sed -i 's|http://security.ubuntu.com/ubuntu|http://port.ubuntu.com|g' /etc/apt/sources.list
fi

# Install sudo and other essential packages if needed
if ! command -v sudo >/dev/null 2>&1; then
    apt update
    apt install -y sudo
fi

# Configure sudoers: allow root to run sudo without password (only if sudo exists)
if command -v sudo >/dev/null 2>&1 && [ ! -f /etc/sudoers.d/nopasswd ]; then
    echo 'root ALL=(ALL) NOPASSWD:ALL' > /etc/sudoers.d/nopasswd
    chmod 440 /etc/sudoers.d/nopasswd
fi

# Optionally install some useful packages if not present
for pkg in bash nano; do
    if ! dpkg -s $pkg >/dev/null 2>&1; then
        apt update
        apt install -y $pkg
    fi
done

# Start the shell
if [ "$#" -eq 0 ]; then
    exec /bin/bash
else
    exec "$@"
fi
