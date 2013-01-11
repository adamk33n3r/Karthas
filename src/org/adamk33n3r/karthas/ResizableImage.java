package org.adamk33n3r.karthas;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.ImageBuffer;
import org.newdawn.slick.SlickException;

public class ResizableImage {
	private Image image;
	private Piece topLeft, topRight, bottomLeft, bottomRight, vertMiddleTop, vertMiddleBottom, horMiddleLeft, horMiddleRight, center;
	private Piece[] pieces;

	public ResizableImage(String path, int x1, int x2, int x3, int y1, int y2, int y3) {
		try {
			image = new org.newdawn.slick.Image(path);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		topLeft = new Piece(image.getSubImage(0, 0, x1, y1), 0, 0);
		topRight = new Piece(image.getSubImage(x1 + x2, 0, x3, y1), x1 + x2, 0);
		bottomLeft = new Piece(image.getSubImage(0, y1 + y2, x1, y3), 0, y1 + y2);
		bottomRight = new Piece(image.getSubImage(x1 + x2, y1 + y2, x3, y3), x1 + x2, y1 + y2);
		vertMiddleTop = new Piece(image.getSubImage(x1, 0, x2, y1), x1, 0);
		vertMiddleBottom = new Piece(image.getSubImage(x1, y1 + y2, x2, y3), x1, y1 + y2);
		horMiddleLeft = new Piece(image.getSubImage(0, y1, x1, y2), 0, y1);
		horMiddleRight = new Piece(image.getSubImage(x1 + x2, y1, x3, y2), x1 + x2, y1);
		center = new Piece(image.getSubImage(x1 + x2, y1 + y2, x2, y2), x1 + x2, y1 + y2);
		pieces = new Piece[9];
		pieces[0] = topLeft;
		pieces[1] = topRight;
		pieces[2] = bottomLeft;
		pieces[3] = bottomRight;
		pieces[4] = vertMiddleTop;
		pieces[5] = vertMiddleBottom;
		pieces[6] = horMiddleLeft;
		pieces[7] = horMiddleRight;
		pieces[8] = center;
	}

	public Image getImage(int width, int height) {
		return image;
	}

	public Image build(int width, int height) {
		ImageBuffer buff = new ImageBuffer(width, height);
		topRight.drawX = topRight.x + width - topLeft.width - vertMiddleTop.width - topRight.width;
		bottomRight.drawX = bottomRight.x + width - bottomLeft.width - vertMiddleBottom.width - bottomRight.width;
		bottomRight.drawY = bottomRight.y + height - topRight.height - horMiddleRight.height - bottomRight.height;
		bottomLeft.drawY = bottomRight.y + height - topLeft.height - horMiddleLeft.height - bottomLeft.height;
		for (int i = 0; i < 4; i++) {
			Piece piece = pieces[i];
			for (int row = piece.y; row < piece.height + piece.y; row++) {
				for (int col = piece.x; col < piece.width + piece.x; col++) {
					Color color = image.getColor(col, row);
					buff.setRGBA(col + piece.drawX - piece.x, row + piece.drawY - piece.y, color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
				}
			}
		}
		vertMiddleTop.drawX = vertMiddleTop.x;
		vertMiddleBottom.drawX = vertMiddleBottom.x;
		vertMiddleBottom.drawY = height - bottomLeft.height;
		int maxTop = width - topLeft.width - topRight.width;
		for (int i = 4; i < 6; i++) {
			Piece piece = pieces[i];
			for (int time = 0; time < maxTop; time++)
				for (int row = piece.y; row < piece.height + piece.y; row++)
					for (int col = piece.x; col < piece.width + piece.x; col++) {
						Color color = image.getColor(col, row);
						//System.out.println("maxTop: " + maxTop + "; " + col + ", " + row + ": " + (col + piece.drawX - piece.x + (piece.width * time)));
						buff.setRGBA(col + piece.drawX - piece.x + (piece.width * time), row + piece.drawY - piece.y, color.getRed(), color.getGreen(),
								color.getBlue(), color.getAlpha());
					}

		}
		horMiddleLeft.drawY = horMiddleLeft.y;
		horMiddleRight.drawY = horMiddleRight.y;
		horMiddleRight.drawX = width - topRight.width;
		int maxLeft = height - topLeft.height - bottomLeft.height;
		for (int i = 6; i < 8; i++) {
			Piece piece = pieces[i];
			for (int time = 0; time < maxLeft; time++)
				for (int row = piece.y; row < piece.height + piece.y; row++)
					for (int col = piece.x; col < piece.width + piece.x; col++) {
						Color color = image.getColor(col, row);
						//System.out.println("maxTop: " + maxTop + "; " + col + ", " + row + ": " + (col + piece.drawX - piece.x + (piece.width * time)));
						buff.setRGBA(col + piece.drawX - piece.x, row + piece.drawY - piece.y + (piece.height * time), color.getRed(), color.getGreen(),
								color.getBlue(), color.getAlpha());
					}

		}
		center.drawX = center.x - 1;
		center.drawY = center.y - 1;
		Piece piece = pieces[8];
		for (int time = 0; time < maxTop; time++)
			for (int time2 = 0; time2 < maxLeft; time2++)
				for (int row = piece.y; row < piece.height + piece.y; row++)
					for (int col = piece.x; col < piece.width + piece.x; col++) {
						Color color = image.getColor(col, row);
						buff.setRGBA(col + piece.drawX - piece.x + (piece.width * time), row + piece.drawY - piece.y + (piece.height * time2), color.getRed(),
								color.getGreen(), color.getBlue(), color.getAlpha());
					}

		return buff.getImage();
	}

	private class Piece {
		public Image image;

		public int x, y, width, height, drawX, drawY;

		public Piece(Image image, int x, int y) {
			this.image = image;
			this.x = x;
			this.y = y;
			this.width = image.getWidth();
			this.height = image.getHeight();
		}
	}

}
