# Transilk 
![Lines of code](https://img.shields.io/badge/lines-1818-444444?style=for-the-badge)
![Java classes](https://img.shields.io/badge/classes-37-444444?style=for-the-badge)
![GitHub repo size](https://img.shields.io/github/repo-size/lasnikprogram/Transilk?color=444444&style=for-the-badge)

### Welcome to Transilk - your go-to translation program!
Transilk is a modern and intuitive program designed to provide seamless translation services to users. 
With the help of the [FreeDict API](https://freedict.org/), the program can automatically download dictionaries and use them to provide translations for any word you input.

![image](https://user-images.githubusercontent.com/61758940/225668443-35083d2e-4aa1-4634-b389-e10a615c5025.png)

The main goal for this school project was to brighten up the field of translation programs with a program that looks better than the rest.
The program was tested on Ubuntu and Windows but should technically work on all Operating Systems.

## Usage
Dictionaries can simply be specified by clicking on a flag icon and typing in the [ISO 639-1 code](https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes) for the language.
The icon and dictionary for the language will be automatically downloaded. 
Users can then simply search a word by typing in the search field.

## Download
Right now only possible by building package:  
In Intellij Idea: `Build` | `Build Artifact` | `Build`, result in `out/jars/`.

Working on a reliable solution.

## Additional Tools

### Libraries (can be found in `libs/` folder)  
[Gson](https://github.com/google/gson)  
[dictzip](https://codeberg.org/miurahr/dictzip-java)  
[Apache Commons Compress™](https://commons.apache.org/proper/commons-compress/)  
[XZ for Java](https://tukaani.org/xz/java.html)

### Online Resources
[FreeDict (API)](https://freedict.org/)  
[Flags for Languages, by ISO 639-1 Code](https://www.unknown.nu/flags/)