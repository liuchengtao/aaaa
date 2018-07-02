package sum.cen.util;

import java.util.ArrayList;
import java.util.List;







import sum.cen.entity.SysMenu;
import sum.cen.entity.SysMenuBtn;
import sum.cen.entity.TreeNode;



public class TreeUtil {
	private final static String MENU_ID="menu_";
	private  final static String BTN_ID="btn_";
	List<SysMenu> rootMenus=null;
	List<SysMenu> childMenus=null;
	List<SysMenuBtn> childBtns=null;
	public TreeUtil(List<SysMenu> rootMenus,List<SysMenu> childMenus){
		this.rootMenus=rootMenus;
		this.childMenus=childMenus;
	}
	public TreeUtil(List<SysMenu> rootMenus,List<SysMenu> childMenus,List<SysMenuBtn> childBtns){
		this.rootMenus = rootMenus;
		this.childMenus = childMenus;
		this.childBtns = childBtns;
	} 
	public List<TreeNode> getTreeNode(){
		return getRootNodes();
	}

	private List<TreeNode> getRootNodes() {
		List<TreeNode> rootNodes=new ArrayList<TreeNode>();
		for(SysMenu menu:rootMenus){
			TreeNode node=MenuToTreeNode(menu); //把菜单对象转换成treenode对象
			  if(node!=null){
				  addChildMenus(node); //对每一个转换后的菜单对象进行判断  是否存在子菜单 添加子菜单集合
			  }
			  rootNodes.add(node);
			
		}
		return rootNodes;
	}
	/**
	 * 封装之后的根菜单对象进行判断  
	 * 是否存在子菜单 添加子菜单集合
	 * @param node
	 */
	private void addChildMenus(TreeNode rootNode) {
		List<TreeNode> childsNodes=new ArrayList<TreeNode>();
		for(SysMenu menu:childMenus){
			if(rootNode.getDataId()==menu.getParentId()){
				TreeNode node=MenuToTreeNode(menu); //转换之后的子菜单
				
				if(childBtns != null && !childBtns.isEmpty()){
					System.out.println("childBtns.size=="+childBtns.size());
					addChlidBtn(node);
				}
				childsNodes.add(node);
			}
			rootNode.setChildren(childsNodes);
		}
		
	}
	/**
	 * 把菜单对象转换成TreeNode对象
	 * @param menu
	 * @return
	 */
	private TreeNode MenuToTreeNode(SysMenu menu) {
		if(menu==null){
			return null;
		}
		TreeNode node=new TreeNode();
		node.setId(MENU_ID+menu.getId());
		node.setDataId(menu.getId());
		node.setText(menu.getName());
		node.setUrl(menu.getUrl());
		node.setParentId(menu.getParentId());
		node.getAttributes().put("type", "0");
		node.getAttributes().put("id", menu.getId());
		return node;
	}
	private TreeNode BtnToNode(SysMenuBtn btn){
		if(btn == null){
			return null;
		}
		TreeNode node = new TreeNode();
		node.setId(BTN_ID+btn.getId());
		node.setDataId(btn.getId());
		node.setText(btn.getBtnName());
		node.setParentId(btn.getMenuid());
		node.getAttributes().put("type", "1");
		node.getAttributes().put("id", btn.getId());
		return node;
	}
	private void addChlidBtn(TreeNode treeNode){
		List<TreeNode> childNodes = new ArrayList<TreeNode>(); 
		for(SysMenuBtn btn : childBtns){
			if(treeNode.getDataId().equals(btn.getMenuid())){
				TreeNode node = BtnToNode(btn);
				childNodes.add(node);
			}
		}
		treeNode.setChildren(childNodes);
	}

}
