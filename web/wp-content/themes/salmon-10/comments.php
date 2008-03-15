<?php // Do not delete these lines
	if ('comments.php' == basename($_SERVER['SCRIPT_FILENAME']))
		die ('Please do not load this page directly. Thanks!');

        if (!empty($post->post_password)) { // if there's a password
            if ($_COOKIE['wp-postpass_' . COOKIEHASH] != $post->post_password) {  // and it doesn't match the cookie
				?>
				
				<p><?php _e("This post is password protected. Enter the password to view comments."); ?><p>
				
				<?php
				return;
            }
        }

		/* This variable is for alternating comment background, thanks Kubrick */
		$oddcomment = 'alt';
?>

<!-- You can start editing here. -->

<?php if ($comments) : ?>
<div class="List">
<h3 id="comments"><strong><?php comments_number(__('No Comments'), __('1 Comment'), __('% Comments')); ?></strong>
<em><?php the_title(); ?></em></h3>
<ol>
<?php foreach ($comments as $comment) : ?>
 <li class="<?php echo $oddcomment; ?>" id="comment-<?php comment_ID() ?>">
 <p class="ListUser"><strong><?php comment_author_link() ?></strong></p>
 <p class="ListDate"><a href="#comment-<?php comment_ID() ?>" title=""><?php comment_date('F jS, Y') ?> at <?php comment_time() ?> <?php edit_comment_link('<strong>Modifica Cometariu</strong>','',''); ?></a></p>
<span class="ListNr"><?php $commentNumber++; echo $commentNumber; ?></span>
   <div class="ListContent">
    <?php comment_text() ?>
   </div>
   <?php if ($comment->comment_approved == '0') : ?>
  <em>Your comment is awaiting moderation.</em>
   <?php endif; ?>  
</li>

<?php if ('alt' == $oddcomment) $oddcomment = ''; else $oddcomment = 'alt';	?>
<?php endforeach; /* end for each comment */ ?>
</ol>
	
<p class="note">
	<?php comments_rss_link(__('<abbr title="Really Simple Syndication">RSS</abbr> feed for comments on this post')); ?>
	<?php if ( pings_open() ) : ?>
	&#183; <a href="<?php trackback_url() ?>" rel="trackback"><?php _e('TrackBack <abbr title="Uniform Resource Identifier">URI</abbr>'); ?></a>
	<?php endif; ?>
	</p>
</div>
<?php else : // this is displayed if there are no comments so far ?>

	<?php if ('open' == $post-> comment_status) : ?> 
		<?php /* No comments yet */ ?>
		
	<?php else : // comments are closed ?>
		<?php /* Comments are closed */ ?>
		<p><?php _e('Comments are closed.'); ?></p>
		
	<?php endif; ?>
<?php endif; ?>


<?php if ('open' == $post-> comment_status) : ?>
<div class="Form">
<h3 id="comments"><strong>Leave a Reply</strong></h3>
  
<?php if ( get_option('comment_registration') && !$user_ID ) : ?>
<p>You must be <a href="<?php echo get_option('siteurl'); ?>/wp-login.php?redirect_to=<?php the_permalink(); ?>">logged in</a> to post a comment.</p>
<?php else : ?>
<form action="<?php echo get_option('siteurl'); ?>/wp-comments-post.php" method="post" id="commentform">
<?php if ( $user_ID ) : ?>
<p>Logged in as <a href="<?php echo get_option('siteurl'); ?>/wp-admin/profile.php"><?php echo $user_identity; ?></a>. <a href="<?php echo get_option('siteurl'); ?>/wp-login.php?action=logout" title="<?php _e('Log out of this account') ?>">Logout &raquo;</a></p>
<?php else : ?>

<p>
<label for="author"><strong>Name</strong> <small><?php if ($req) _e('(required)'); ?></small></label>
<input type="text" name="author" id="author" value="<?php echo $comment_author; ?>" tabindex="1" class="TextField" style="width: 400px;" />
</p>
		
<p>
<label for="email"><strong>E-mail</strong> <small>(<?php if ($req) _e('required, '); ?>never displayed)</small></label>
<input type="text" name="email" id="email" value="<?php echo $comment_author_email; ?>" tabindex="2" class="TextField" style="width: 400px;" />
</p>
		
<p>
<label for="url"><abbr title="Uniform Resource Identifier"><strong>URI</strong></abbr></label>
<input type="text" name="url" id="url" value="<?php echo $comment_author_url; ?>" tabindex="3" class="TextField" style="width: 400px;" />
</p>

<?php endif; ?>
<p>
<label for="comment"><strong>Comment</strong></label>
<textarea name="comment" id="comment" rows="10" tabindex="4" class="TextArea" style="width: 400px;"></textarea>
</p>

<p>
<input name="SubmitComment" type="image" class="SubmitComment" onmouseover="javascript:changeSty('SubmitCommentIE');" onmouseout="javascript:changeSty('SubmitComment');"  title="Post Message" src="<?php bloginfo('template_url'); ?>/images/transparent.gif" alt="Post Message" />
<input type="hidden" name="comment_post_ID" value="<?php echo $id; ?>" />
</p>
<?php do_action('comment_form', $post->ID); ?>
</form>
<?php endif; // If registration required and not logged in ?>
<?php endif; // if you delete this the sky will fall on your head ?>
</div>