public class PhotoManager {

	private BST<LinkedList<Photo>> bat;
	private LinkedList<Photo> allData;

	public PhotoManager() {
		bat = new BST<LinkedList<Photo>>();
		allData = new LinkedList<Photo>();
	}

	public void addPhoto(Photo p) {
		if (p == null || p.getTags() == null || findPhoto(p.getPath()) != null)
			return;
		if (allData.empty())
			allData.insert(p);
		else {
			allData.findFirst();
			while (!allData.last()) {
				if (allData.retrieve().getPath().equals(p.getPath()))
					return;
				allData.findNext();
			}
			if (allData.last() && !(allData.retrieve().getPath().equals(p.getPath())))
				allData.insert(p);
			else if (allData.last() && (allData.retrieve().getPath().equals(p.getPath())))
				return;
		}
		if (p.getTags().empty())
			return;
		LinkedList<String> allTags = new LinkedList<String>();
		allTags = p.getTags();
		allTags.findFirst();
		while (!allTags.last()) {
			if (bat.findKey(allTags.retrieve())) {
				LinkedList<Photo> bstData = new LinkedList<Photo>();
			     bstData = bat.retrieve();
				bstData = isAdded(bstData, p);
				bat.insert(allTags.retrieve(), bstData);
			} else {
				LinkedList<Photo> allData = new LinkedList<Photo>();
				allData.insert(p);
				bat.insert(allTags.retrieve(), allData);
			}
			allTags.findNext();
		}
		if (bat.findKey(allTags.retrieve())) {
			LinkedList<Photo> bstData = new LinkedList<Photo>();
			bstData = bat.retrieve();
			isAdded(bstData, p);
			bat.insert(allTags.retrieve(), bstData);
		} else {
			LinkedList<Photo> allData = new LinkedList<Photo>();
			allData.insert(p);
			bat.insert(allTags.retrieve(), allData);
		}
	}

	public void deletePhoto(String path) {
		allData.findFirst();
		while (!allData.last()) {
			if (allData.retrieve().getPath().equals(path)) {
				allData.remove();
				break;
			}
			allData.findNext();
		}
		if (allData.last() && (allData.retrieve().getPath().equals(path)))
			allData.remove();
		Photo photo = findPhoto(path);
		if (photo == null)
			return;
		LinkedList<String> allTags = new LinkedList<String>();
		allTags = photo.getTags();
		allTags.findFirst();
		while (!allTags.last()) {
			if (bat.findKey(allTags.retrieve())) {
				LinkedList<Photo> photos = new LinkedList<Photo>();
				photos = bat.retrieve();
				if (isDeleted(photos, photo).empty()) {
					if (photos.empty())
						bat.removeKey(allTags.retrieve());
				} 
			}
			allTags.findNext();
		}
		if (bat.findKey(allTags.retrieve())) {
			LinkedList<Photo> allData = new LinkedList<Photo>();
			allData = bat.retrieve();
			if (isDeleted(allData, photo).empty()) {
				if (allData.empty())
					bat.removeKey(allTags.retrieve());
			} 
		}
	}

	public BST<LinkedList<Photo>> getPhotos() {
		return bat;
	}

	public LinkedList<Photo> getAllPhotos() {
		LinkedList<Photo> allData = new LinkedList<Photo>();
		getAllPhotos(bat.getRoot(), allData);
		return allData;
	}

	private void getAllPhotos(BSTNode<LinkedList<Photo>> root, LinkedList<Photo> allData) {
		if (root == null)
			return;
		if (root.left == null && root.right == null) {
			merge(root.data, allData);
		} else if (root.right == null) {
			getAllPhotos(root.left, allData);
			merge(root.data, allData);
		} else if (root.left == null) {
			merge(root.data, allData);
			getAllPhotos(root.right, allData);
		} else {
			getAllPhotos(root.left, allData);
			merge(root.data, allData);
			getAllPhotos(root.right, allData);
		}
	}

	private boolean merge(LinkedList<Photo> BSTData, LinkedList<Photo> allData) {
		if (BSTData.empty())
			return false;
		if (!allData.empty()) {
			BSTData.findFirst();
			while (!BSTData.last()) {
				allData.findFirst();
				while (!allData.last()) {
					if (allData.retrieve().getPath().equals(BSTData.retrieve().getPath())) {
						if (!BSTData.last()) {
							BSTData.findNext();
							allData.findFirst();
						} else {
							break;
						}
					}
					allData.findNext();
				}
				if (!allData.retrieve().getPath().equals(BSTData.retrieve().getPath())) {
					allData.insert(BSTData.retrieve());
				}
				if (BSTData.last())
					break;
				BSTData.findNext();
			}
			allData.findFirst();
			while (!allData.last()) {
				if (allData.retrieve().getPath().equals(BSTData.retrieve().getPath()))
					break;
				allData.findNext();
			}
			if (!allData.retrieve().getPath().equals(BSTData.retrieve().getPath())) {
				allData.insert(BSTData.retrieve());
			}
			return true;
		} else {
			BSTData.findFirst();
			while (!BSTData.last()) {
				allData.insert(BSTData.retrieve());
				BSTData.findNext();
			}
			allData.insert(BSTData.retrieve());
			return true;
		}
	}


	private LinkedList<Photo> isAdded(LinkedList<Photo> allData, Photo photo) {
		if (allData == null)
			return null;
		allData.findFirst();
		while (!allData.last()) {

			allData.findNext();
		}

		allData.insert(photo);

		return allData;
	}

	private LinkedList<Photo> isDeleted(LinkedList<Photo> allData, Photo photo) {
		if (allData == null)
			return null;
		else if (allData.empty())
			return allData;
		allData.findFirst();
		while (!allData.last()) {
			if (allData.retrieve().getPath().equals(photo.getPath())) {
				allData.remove();
				return allData;
			}
			allData.findNext();
		}
		if (allData.retrieve().getPath().equals(photo.getPath())) {
			allData.remove();
			return allData;
		}
		if (allData.empty())
			allData = null;
		return allData;
	}

	private Photo findPhoto(String path) {
		LinkedList<Photo> allPhotos = new LinkedList<Photo>();
		 allPhotos = getAllPhotos();
		if (allPhotos.empty())
			return null;
		allPhotos.findFirst();
		while (!allPhotos.last()) {
			if (allPhotos.retrieve().getPath().equals(path)) {
				return allPhotos.retrieve();
			}
			allPhotos.findNext();
		}
		if (allPhotos.retrieve().getPath().equals(path))
			return allPhotos.retrieve();
		return null;
	}

	public LinkedList<Photo> getAllData() {
		return allData;
	}
}
