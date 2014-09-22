GridPicker
==========

GridPicker is a custom view that improves on the built-in android number picker widget and gives the user more 
flexibility in terms of usage. This library is still in testing stages so be aware that there are some bugs that need fixing.

Usage
======

For a working implementation, see the `/sample` folder

Include the widget in your layout.

```
<com.rohitramkumar.gridpicker.app.GridPicker
        android:id="@+id/sample_picker"
        android:layout_width="500dp"
        android:layout_height="500dp"
        app:startValue="1"
        app:increment="1 
        app:numColumns="4"
        app:numRows="4"/>
```

Notice the four custom attributes that pertain to the GridPicker.

* startValue: This is the value that the grid picker starts at (default is 1).
* increment: The increment value (default is 1).
* numColumns: The number of columns in the grid (default is 3).
* numRows: The number of rows in the grid (default is 3).


In your `onCreate`, initialize the widget like you would with any other view.

```
GridPicker gridPicker = (GridPicker) findViewById(R.id.sample_picker);
```

Use the method `setClickListeners` to attach onClickListeners to your widget.

```
gridPicker.setClickListeners(mClickListener);
```

Additional Capabilities
=======================

If you wish to add state list drawables to the buttons in the widget, then utilize `setDrawableResources`.

```
gridPicker.setDrawableResources(drawable1, drawable2, drawable3);
```

Developed By
============

Rohit Ramkumar - rohitramkumar13@gmail.com
