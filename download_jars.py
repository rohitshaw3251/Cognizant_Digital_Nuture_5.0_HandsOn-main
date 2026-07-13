import os
import urllib.request

urls = {
    "junit-platform-console-standalone-1.10.2.jar": "https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.2/junit-platform-console-standalone-1.10.2.jar",
    "mockito-core-5.11.0.jar": "https://repo1.maven.org/maven2/org/mockito/mockito-core/5.11.0/mockito-core-5.11.0.jar",
    "byte-buddy-1.14.12.jar": "https://repo1.maven.org/maven2/net/bytebuddy/byte-buddy/1.14.12/byte-buddy-1.14.12.jar",
    "byte-buddy-agent-1.14.12.jar": "https://repo1.maven.org/maven2/net/bytebuddy/byte-buddy-agent/1.14.12/byte-buddy-agent-1.14.12.jar",
    "objenesis-3.3.jar": "https://repo1.maven.org/maven2/org/objenesis/objenesis/3.3/objenesis-3.3.jar",
    "slf4j-api-2.0.12.jar": "https://repo1.maven.org/maven2/org/slf4j/slf4j-api/2.0.12/slf4j-api-2.0.12.jar",
    "slf4j-simple-2.0.12.jar": "https://repo1.maven.org/maven2/org/slf4j/slf4j-simple/2.0.12/slf4j-simple-2.0.12.jar"
}

lib_dir = "e:/Projects/Java FSE/week1/lib"
os.makedirs(lib_dir, exist_ok=True)

for name, url in urls.items():
    dest = os.path.join(lib_dir, name)
    if not os.path.exists(dest):
        print(f"Downloading {name}...")
        try:
            urllib.request.urlretrieve(url, dest)
            print(f"Successfully downloaded {name}")
        except Exception as e:
            print(f"Error downloading {name}: {e}")
    else:
        print(f"{name} already exists.")
