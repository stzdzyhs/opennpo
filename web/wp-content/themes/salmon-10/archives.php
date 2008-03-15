<?php
/*
Template Name: Archives
*/
?>

<?php get_header(); ?>

<div id="bgcontain">
<!-- container START -->
<div class="container"> 

<!-- Side Central START -->
<div class="SC">
<h2>Entries, sorted by month</h2>
			<?php smartArchives(); ?>
</div>

<!--  Side Central END -->
<?php get_sidebar(); ?>
<?php get_footer(); ?>