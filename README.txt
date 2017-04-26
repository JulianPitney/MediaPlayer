Julian Pitney (JulianPitney)
Quinton Belcastro (Quinton-Bel)



Build Requirements:

	1. Android Studio (Latest version) (Contains JRE and Android SDK)
	
Build and Run:

	1. Clone repo to local system -> [https://github.com/Quinton-Bel/MediaPlayer.git]
	2. Open project in Android Studio
	3. Android Studio will prompt with dependency download. Download the dependency.
	4. Put your Android device into developer mode -> [http://blog.syncios.com/enable-developer-optionsusb-debugging-mode-on-devices-with-android-4-2-jelly-bean/]
	5. Connect device to PC using data transfer capable cable.
	6. Build application in Android Studio (Should just be able to select build if the dependencies have been downloaded and installed correctly)
	7. In Android Studio select "Run" (A prompt will open asking where you would like to run, select your connected Android device)
	8. Once the build finishes, the application will launch on your device. It will crash on the first run. 
	   To fix this, open your device (Android 6.0.1) and select [Settings->Applications->Application Manager->Music->Permissions->Enable Storage] 
	9. Rerun the app on your phone.

	First Important Note: Place some music in the Music folder of your Android device (All music files should be in the root of directory */Music).
			      There is no need to organize the music into Artist/Album directories, the application checks a database on the network
			      and will figure out how to organize everything. If you are a normal person and have no local music,
			      -> TestMaterials are located under app/TestMaterials in the repo.

	Second Important Note: We had some trouble getting the UI to refresh correctly while waiting for threads that are contacting the network to finish.
			       You will need to swipe back and fourth from Playlists->Artists->Albums->Songs a few times so the UI refreshes after the network data
			       is finished loading.


Optional:

	1. Playlists can be created with a rooted Android device or using an emulator in Android Studio.
	   If you want to test playlist functionality place the CoolPlaylist.txt in the emulator under data/data/com.example.quinton.mediaplayer/Playlists 
	   (if playlists doesn't exist it will be automatically created)

Run Requirements:

	1. Network Connection.

Additional Notes:

	1. Contact either of us if you can't get it working.

	
