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

<p> public void selectImage() {</p>

<p> imageSelection = new ImageSelection(MainActivity.this);<br />
 final CharSequence[] items = {"Take Photo", "Choose from Library",<br />
 "Cancel"};<br />
 AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);<br />
 builder.setTitle("Add Photo!");<br />
 builder.setItems(items, new DialogInterface.OnClickListener() {<br />
 @Override<br />
 public void onClick(DialogInterface dialog, int item) {</p>

<p>
 if (items[item].equals("Take Photo")) {<br />
 boolean isComprase = true;<br />
 imageSelection.openCamera(isComprase);</p>

<p> } else if (items[item].equals("Choose from Library")) {<br />
 boolean isComprase = true;<br />
 imageSelection.openGallery(isComprase);<br />
 } else if (items[item].equals("Cancel")) {<br />
 dialog.dismiss();<br />
 }<br />
 }<br />
 });<br />
 builder.show();<br />
 }</p>
 
</p>
