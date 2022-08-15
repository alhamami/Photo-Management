# Photo-Management
## Introduction
- The role of a photo management application is to organize photographs so that they can
be easily accessed. In order to help organize photos, the user can provide tags to describe
the content of the photos. A tag is a keyword associated with a photo. For example, we
can associate the tag ”vacation” to any photo taken during a vacation. In Table 1, you
may find some examples of photos and the tags that are used to describe them.

<p align="center">
    ![alt text](https://i.imgur.com/CX3nvB5.png)
</p>
 
- The photo manager organizes the photos into albums created by the user. An album
is identified by a unique name and regroups photos that satisfy certain conditions. For
the purpose of this assignment, the conditions used to create albums consist in a sequence
of tags separated by "AND":
Tag1 AND Tag2 AND Tag3
- Photos that contain all specified tags will appear in the album. An empty tag list matches
all photos.

### Example 1. Using the photos of Table 1, the album with the condition bear, will contain two photos (that of the panda and the grizzly bear). The album with the condition
animal AND grass will contain four photos (hedgehog, grizzly bear, fox and panda). 
The albbum with no tags will contain all eight photos.

## Inverted index
- In order to accelerate the search for photos, it is possible to store the tags in an inverted
index. The idea is that instead of having the photos point to the tags, the inverted index
will store all the tags, and each tag will point to all the photos that contain it.
The following is an example showing a partial inverted index for the photos shown
above:
> animal → hedgehog.jpg, bear.jpg, fox.jpg, panda.jpg, wolf.jpg, racoon.jpg
> apple → hedgehog.jpg
> bear → bear.jpg, panda.jpg
> black → butterfly2.jpg
> butterfly → butterfly1.jpg, butterfly2.jpg
...

- I represented the photos-tags association using an inverted index stored in the class
PhotoManager.
- I used a data structure that allows O(log n) in average to search for a tag.
