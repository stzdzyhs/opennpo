<?php get_header(); query_posts('posts_per_page=5'); ?>
<div id="bgcontain">
<!-- container START -->
<div class="container"> 

<!-- Side Central START -->
<div class="SC">

<?php if (have_posts()) : ?>
<?php while (have_posts()) : the_post(); 
?>
		<div class="Post">
			<div class="PostHead">
		     <h1><a title="Permanent Link to <?php the_title(); ?>" href="<?php the_permalink() ?>" rel="bookmark"><?php the_title(); ?></a></h1>
			 </div>
			 
			 <div class="PostInfo">
			 <ul>
			  <? if (!is_single()){ ?> <li class="Comments" style="background: none;"><?php comments_popup_link(__('No Comments'), __('1 Comment'), __(' % Comments '), 'commentslink', __('Comments off')); ?></li><? } ?>
			  <li class="Author">Posted by <?php the_author() ?></li>
			  <li class="Category">Posted in <?php the_category(', ') ?></li>
			 </ul>
			 </div>
			
			
			<div class="PostContent">
			  <?php the_content(''); ?>
			  <div class="PostDetails">
			   <span class="ReadOn"><a href="<?php the_permalink() ?>">Read On</a></span>
			   <span class="Subscribe"><a href="<?php bloginfo('rss2_url'); ?>">Subscribe</a></span>
			   <?php if(!is_single()){ ?><span class="NrComments"><?php comments_popup_link(__('no comments'), __('1 comment'), __(' % comments '), 'commentslink', __('comments off')); ?></span><? } ?>
			   <span class="AddOpinion"><a href="<?php the_permalink() ?>">Add Your Opinion</a></span> 
			  </div>
  
			 </div>

 	 	
<p class="PostDate">
   <strong class="month"><?php the_time('M'); ?></strong>
   <strong class="day"><?php the_time('d'); ?></strong>
 </p> 
</div>

<?php endwhile; ?>
<?php if(is_single()){
				comments_template(); 
				}else{?>
<!--<div class="navigation">
 <div class="alignleft"><?php posts_nav_link('','','&laquo; Previous Entries') ?></div>
 <div class="alignright"><?php posts_nav_link('','Next Entries &raquo;','') ?></div>
</div>-->
<?php } ?>
	
<?php else : ?>

<h2 class="center">Not Found</h2>
<p class="center"><?php _e("Sorry, but you are looking for something that isn't here."); ?></p>

<?php endif; ?> 
</div>
<!--  Side Central END -->
<?php get_sidebar(); ?>
<?php get_footer(); ?>