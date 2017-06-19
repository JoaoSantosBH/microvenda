<%-- 
    Document   : aaa
    Created on : Aug 31, 2015, 1:49:18 PM
    Author     : joaosantos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<meta charset="utf-8">
      <!-- T E S T E -->

  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script type="text/javascript" >
$(document).ready(function($) {
        var availableTags = [
        "ActionScript",
        "AppleScript",
        "Asp",
        "BASIC",
        "C",
        "C++",
        "Clojure",
        "COBOL",
        "ColdFusion",
        "Erlang",
        "Fortran",
        "Groovy",
        "Haskell",
        "Java",
        "JavaScript",
        "Lisp",
        "Perl",
        "PHP",
        "Python",
        "Ruby",
        "Scala",
        "Scheme"
        ];
        $("#tags").autocomplete({
        source: availableTags
        });
        });
</script>
<!-- T E S T E -->
</head>
<body>
 
<div class="">
  <label for="tags">Tags: </label>
  <input id="tags">
</div>
    <!--  H T M L 5 V E R S I O N not compatible safari and IE <=9-->
    <input type="text" name="srch" id="srch" list="datalist1">
<datalist id="datalist1">
  <option value="Canada">
  <option value="China">
  <option value="Mexico">
  <option value="United Kingdom">
  <option value="United States of America">
  <option value="Uruguay">
</datalist>
    
</html>
