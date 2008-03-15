<?php get_header(); ?>
<div id="bgcontain">
<!-- container START -->
<div class="container"> 

<!-- Side Central START -->
<div class="SC">

	<?php if (have_posts()) : ?>

		<h2 class="Heading">Search Results</h2>

		<div class="navigation">
			<div class="alignleft"><?php next_posts_link('&laquo; Previous Entries') ?></div>
			<div class="alignright"><?php previous_posts_link('Next Entries &raquo;') ?></div>
		</div>


		<?php while (have_posts()) : the_post(); ?>

		<div class="Post">
			<div class="PostHead">
		     <h1><a title="Permanent Link to <?php the_title(); ?>" href="<?php the_permalink() ?>" rel="bookmark"><?php the_title(); ?></a></h1>
			 </div>
			 
			 <div class="PostInfo">
			 <ul>
			  <? if (!is_single()){ ?> <li class="Comments" style="background: none;"><a href="#"><?php comments_popup_link(__('No Comments'), __('1 Comment'), __(' % Comments '), 'commentslink', __('Comments off')); ?></a></li><? } ?>
			  <li class="Author">Posted by <?php the_author() ?></li>
			  <li class="Category">Posted in <?php the_category(', ') ?></li>
			 </ul>
			 </div>
			
			
			<div class="PostContent">
			  <?php the_content(''); ?>
			</div>
			  <ul class="PostDetails">
			   <li><a class="ReadOn" href="<?php the_permalink() ?>">Read On</a></li>
			   <!--<li><a class="Subscribe" href="<?php bloginfo('rss2_url'); ?>">Subscribe</a></li>-->
			   <li><?php akst_share_link(); ?></li>
			   <?php if(!is_single()){ ?><li class="NrComments"><?php comments_popup_link(__('no comments'), __('1 comment'), __(' % comments '), 'commentslink', __('comments off')); ?></li><? } ?>
			   <li><a class="AddOpinion" href="<?php the_permalink() ?>">Add Your Opinion</a></li> 
			 </ul>	

 	 	
<p class="PostDate">
   <strong class="month"><?php the_time('M'); ?></strong>
   <strong class="day"><?php the_time('d'); ?></strong>
 </p> 
</div>

		<?php endwhile; ?>

		<div class="navigation">
			<div class="alignleft"><?php next_posts_link('&laquo; Previous Entries') ?></div>
			<div class="alignright"><?php previous_posts_link('Next Entries &raquo;') ?></div>
		</div>

	<?php else : ?>

		<h2 class="center">No posts found. Try a different search?</h2>
		<?php include (TEMPLATEPATH . '/searchform.php'); ?>

	<?php endif; ?>
</div>
<!--  Side Central END -->
<?php get_sidebar(); ?>
<?php get_footer(); ?>