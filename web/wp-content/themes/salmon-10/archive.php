<?php get_header(); ?>
<div id="bgcontain">
<!-- container START -->
<div class="container"> 

<!-- Side Central START -->
<div class="SC">
    <?php if (have_posts()) : ?>
    <?php $post = $posts[0]; // Hack. Set $post so that the_date() works. ?>
    <?php /* If this is a category archive */ if (is_category()) { ?>				
    <h2 class="Heading">Archive for the '<?php echo single_cat_title(); ?>' Category <span class="tagline">Grouped Archives</span></h2>
    <?php /* If this is a daily archive */ } elseif (is_day()) { ?>
    <h2 class="Heading">Archive for <?php the_time('F jS, Y'); ?> <span class="tagline">Daily Archives</span></h2>
    <?php /* If this is a monthly archive */ } elseif (is_month()) { ?>
    <h2 class="Heading">Archive for <?php the_time('F, Y'); ?> <span class="tagline">Monthly Archives</span></h2>
    <?php /* If this is a yearly archive */ } elseif (is_year()) { ?>
    <h2 class="Heading">Archive for <?php the_time('Y'); ?> <span class="tagline">Yearly Archives</span></h2>
    <?php /* If this is a paged archive */ } elseif (isset($_GET['paged']) && !empty($_GET['paged'])) { ?>
    <h2 class="Heading">Blog Archives <span class="tagline">General Archives</span></h2>
    <?php } ?>
    
    <?php while (have_posts()) : the_post(); ?>
    <div class="Post">
        <div class="PostHead">
            <h1><a title="Permanent Link to <?php the_title(); ?>" href="<?php the_permalink(); ?>" rel="bookmark"><?php the_title(); ?></a></h1>
        </div>
        
        <div class="PostInfo">
            <ul>
                <? if (!is_single()){ ?> <li class="Comments" style="background: none;"><a href="#"><?php comments_popup_link(__('No Comments'), __('1 Comment'), __(' % Comments '), 'commentslink', __('Comments off')); ?></a></li><? } ?>
                <li class="Author">Posted by <?php the_author(); ?></li>
                <li class="Category">Posted in <?php the_category(', '); ?></li>
            </ul>
        </div>
        
        
        <div class="PostContent">
            <?php the_content(''); ?>
        </div>
        <ul class="PostDetails">
            <li><a class="ReadOn" href="<?php the_permalink(); ?>">Read On</a></li>
            <!--<li><a class="Subscribe" href="<?php bloginfo('rss2_url'); ?>">Subscribe</a></li>-->
<!--<li><?php //akst_share_link(); ?></li>-->
<?php if(!is_single()){ ?><li class="NrComments"><?php comments_popup_link(__('no comments'), __('1 comment'), __(' % comments '), 'commentslink', __('comments off')); ?></li><? } ?>
            <li><a class="AddOpinion" href="<?php the_permalink(); ?>">Add Your Opinion</a></li> 
        </ul>	
        
        
        <p class="PostDate">
            <strong class="month"><?php the_time('M'); ?></strong>
            <strong class="day"><?php the_time('d'); ?></strong>
        </p> 
    </div>
    
    <?php endwhile; ?>
    <?php else : ?>
    
    <h2>Not Found &mdash; There's no articles that fit your criteria <span class="tagline">Why don't you try again?</span></h2>
    <?php endif; ?>
    <div id="bookmarks">
        <?php if (is_category()) {
        echo '<h2 class="Heading">Category Archives <span class="tagline">Other categories</span></h2>';
        echo '<ul>';
        wp_list_cats('sort_column=name'); 
        echo '</ul>';
        } else {
        echo '<h2 class="Heading">Monthly Archives <span class="tagline">Other dates of posting</span></h2>';
        smartArchives();
        } ?>
    </div>
</div>
<!--  Side Central END -->
<?php get_sidebar(); ?>
<?php get_footer(); ?>