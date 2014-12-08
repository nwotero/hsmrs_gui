package src.main.java.com.github.hsmrs_gui.project.view.robot;

import java.util.List;

import src.main.java.com.github.hsmrs_gui.project.view.list.ListItem;
import src.main.java.com.github.hsmrs_gui.project.view.list.ListItemRenderer;
import src.main.java.com.github.hsmrs_gui.project.view.list.SRList;
import src.main.java.com.github.hsmrs_gui.project.model.Robot;

public class RobotListItemRenderer implements ListItemRenderer<Robot> {
	
	public RobotListItemRenderer() {
	}

	@Override
	public void createRenderedListComponents(SRList<Robot> listPanel,
			List<ListItem<Robot>> listItems) {
		for (ListItem<Robot> listItem : listItems) {
			RobotListComponent renderableComponent = new RobotListComponent();
			listItem.setRenderableComponent(renderableComponent);
			listItem.setComponent(renderableComponent.create(listItem));
		}
		
	}

	@Override
	public void updateRenderedListComponents(SRList<Robot> listPanel,
			List<ListItem<Robot>> listItems) {
		for (ListItem<Robot> listItem : listItems) {
			listItem.update();
		}
		
	}
}
