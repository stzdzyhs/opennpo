<?php get_header(); ?>
<div id="bgcontain">
<!-- container START -->
<div class="container"> 

<!-- Side Central START -->
<div class="SC">
<?php if (have_posts()) : while (have_posts()) : the_post(); ?>
<div class="SinglePage">
<!--<h1><?php the_title(); ?> <span class="tagline"><?php edit_post_link('Edit this entry.', ' ', ''); ?></span></h2>-->
<?php the_content('Read the rest of this page &raquo;'); ?>
<?php link_pages('<p><strong>Pages:</strong> ', '</p>', 'number'); ?>
</div>
<?php endwhile; endif; ?>
</div>
<!--  Side Central END -->
<?php get_sidebar(); ?>
<?php get_footer(); ?>