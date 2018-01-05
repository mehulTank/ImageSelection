<p>Step 1 : Add it in your root build.gradle at the end of repositories:</p>

<p>allprojects {<br />
 repositories {<br />
 ...<br />
 maven { url 'https://jitpack.io' }<br />
 }<br />
 }</p>

<p>Step 2. Add the dependency</p>

<p> dependencies {<br />
 compile 'com.github.mehulTank:ImageSelection:V.1.0'<br />
 }</p>

<p>
 
 <p>Select Image Dialog </p>

<p>public void selectImage() {<br />
 
// open camera 
boolean isComprase = true;
imageSelection.openCamera(isComprase);

// open gallary 

boolean isComprase = true;
imageSelection.openGallery(isComprase);


}</p>
 
</p>
