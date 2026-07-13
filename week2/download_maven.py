import os
import urllib.request
import zipfile

url = "https://archive.apache.org/dist/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip"
dest_zip = "e:/Projects/Java FSE/week2/maven.zip"
extract_dir = "e:/Projects/Java FSE/week2/maven"

os.makedirs(os.path.dirname(dest_zip), exist_ok=True)

if not os.path.exists(dest_zip):
    print("Downloading Apache Maven 3.9.6...")
    try:
        urllib.request.urlretrieve(url, dest_zip)
        print("Successfully downloaded Maven zip")
    except Exception as e:
        print(f"Error downloading Maven: {e}")
        exit(1)

if not os.path.exists(extract_dir):
    print("Extracting Maven...")
    try:
        with zipfile.ZipFile(dest_zip, 'r') as zip_ref:
            zip_ref.extractall(extract_dir)
        print("Successfully extracted Maven")
    except Exception as e:
        print(f"Error extracting Maven: {e}")
        exit(1)
else:
    print("Maven already extracted.")
