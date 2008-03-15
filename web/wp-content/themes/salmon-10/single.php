<?php get_header(); ?>
<div id="bgcontain">
<!-- container START -->
<div class="container"> 

<!-- Side Central START -->
<div class="SC">

  <?php if (have_posts()) : while (have_posts()) : the_post(); ?>

		<div class="Post" id="post-<?php the_ID(); ?>">
			<div class="PostHead">
		     <h1><a href="<?php echo get_permalink() ?>" rel="bookmark" title="Permanent Link: <?php the_title(); ?>"><?php the_title(); ?></a></h1>
			 </div>
			 
			 <div class="PostInfo">
			 <ul>
			  <? if (!is_single()){ ?> <li class="Comments"><a href="#"><?php comments_popup_link(__('No Comments'), __('1 Comment'), __(' % Comments '), 'commentslink', __('Comments off')); ?></a></li><? } ?>
			  <li class="Author">Posted by <?php the_author() ?></li>
			  <li class="Category">Posted in <?php the_category(', ') ?></li>
			 </ul>
			 </div>
			 
			<div class="PostContent">
			<?php the_content('<p class="serif">Read the rest of this entry &raquo;</p>'); ?>
            <?php link_pages('<p><strong>Pages:</strong> ', '</p>', 'number'); ?>
             </div>
			
<p class="PostDate">
   <strong class="month"><?php the_time('M'); ?></strong>
   <strong class="day"><?php the_time('d'); ?></strong>
 </p> 
			 	
<small>
This entry was posted
<?php /* This is commented, because it requires a little adjusting sometimes.
You'll need to download this plugin, and follow the instructions:
http://binarybonsai.com/archives/2004/08/17/time-since-plugin/ */
/* $entry_datetime = abs(strtotime($post->post_date) - (60*120)); echo time_since($entry_datetime); echo ' ago'; */ ?> 
on <?php the_time('l, F jS, Y') ?> at <?php the_time() ?>
and is filed under <?php the_category(', ') ?>.
You can follow any responses to this entry through the <?php comments_rss_link('RSS 2.0'); ?> feed. 

<?php if (('open' == $post-> comment_status) && ('open' == $post->ping_status)) {
// Both Comments and Pings are open ?>
You can <a href="#respond">leave a response</a>, or <a href="<?php trackback_url(true); ?>" rel="trackback">trackback</a> from your own site.

<?php } elseif (!('open' == $post-> comment_status) && ('open' == $post->ping_status)) { 
// Only Pings are Open ?>
Responses are currently closed, but you can <a href="<?php trackback_url(true); ?> " rel="trackback">trackback</a> from your own site.
<?php } elseif (('open' == $post-> comment_status) && !('open' == $post->ping_status)) {
// Comments are open, Pings are not ?>
You can skip to the end and leave a response. Pinging is currently not allowed.
<?php } elseif (!('open' == $post-> comment_status) && !('open' == $post->ping_status)) {
// Neither Comments, nor Pings are open ?>
Both comments and pings are currently closed.
<?php } edit_post_link('Edit this entry.','',''); ?>
</small>
					
</div>
		
<div class="Comments">
<?php comments_template(); ?>
</div>

<?php endwhile; else: ?>
<p>Sorry, no posts matched your criteria.</p>
<?php endif; ?> 
</div>
<!--  Side Central END -->
<?php get_sidebar(); ?>
<?php get_footer(); ?>
