package  util;

import java.util.Collection;

/**
 * ��ҳ������
 * 
 */
public class Page {

	private int currentPageNumber = 1; // ��ǰҳ����,��ǰ�ǵڼ�ҳ

	/* private int Countitem; */
	private int totalPage; // �ܵ�ҳ����

	private long totalNumber; // �ܵļ�¼��

	private int itemInPage; // ��ǰҳ��ĵ�һ����¼������ţ�

	private int itemsPerPage; // ÿһҳ�ļ�¼��

	private boolean next; // �Ƿ�����һҳ

	private boolean previous; // �Ƿ�����һҳ

	private Collection list; // ��ǰҳ���¼����

	private Object conditonObject; // ��ѯ��������

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public void setTotalNumber(long totalNumber) {
		this.totalNumber = totalNumber;
	}

	public void setItemInPage(int itemInPage) {
		this.itemInPage = itemInPage;
	}

	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public void setPrevious(boolean previous) {
		this.previous = previous;
	}

	public void setConditonObject(Object conditonObject) {
		this.conditonObject = conditonObject;
	}

	public Object getConditonObject() {
		return conditonObject;
	}
	public Page(){
		
	}
	public Page(int totalNumber, int itemPerPage) {
		this.totalNumber = totalNumber;
		this.itemsPerPage = itemPerPage;
		this.totalPage = (totalNumber + itemPerPage - 1) / itemPerPage;
		// if (totalNumber == 0)
		// this.currentPageNumber = 0;
		flush();
	}

	/**
	 * ˢ��ҳ�����ԣ�ˢ����һҳ����һҳ��״̬
	 */
	private void flush() {
		this.setNext();
		this.setPrevious();
		this.itemInPage = (currentPageNumber - 1) * itemsPerPage;
	}

	private void setNext() {

		if (this.currentPageNumber >= this.totalPage) {
			this.next = false;
		} else {
			this.next = true;
		}
	}

	private void setPrevious() {
		if (currentPageNumber == 1 || currentPageNumber == 0) {
			this.previous = false;
		} else {
			this.previous = true;
		}
	}

	/**
	 * �����еļ�¼��
	 * 
	 * @return
	 */
	public long getTotalNumber() {
		return totalNumber;
	}

	// public void setTotalNumber(int totalNumber) {
	// this.totalNumber = totalNumber;
	// }

	public int getItemInPage() {
		return itemInPage;
	}

	// public void setItemInPage(int itemInPage) {
	// this.itemInPage = itemInPage;
	// }

	public int getItemsPerPage() {
		return itemsPerPage;
	}

	// public void setItemsPerPage(int itemsPerPage) {
	// this.itemsPerPage = itemsPerPage;
	// }

	/**
	 * �Ƿ�����һҳ
	 * 
	 * @return �������һҳ������true�����򷵻�false
	 */
	public boolean isNext() {
		return next;
	}

	/**
	 * �Ƿ�����һҳ
	 * 
	 * @return �������һҳ������true�����򷵻�false
	 */
	public boolean isPrevious() {
		return previous;
	}

	/**
	 * �õ������
	 * 
	 * @return
	 */
	public Collection getList() {
		return list;
	}

	/**
	 * ���ý����
	 * 
	 * @param list
	 */
	public void setList(Collection list) {
		this.list = list;
	}

	public int getTotalPage() {
		return totalPage;
	}

	// public void setTotalPage(int totalPage) {
	// this.totalPage = totalPage;
	// }

	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	/**
	 * ���õ�ǰҪ���ҵ�ҳ��ֵ
	 * 
	 * @param currentPageNumber
	 */
	public void setCurrentPageNumber(int currentPageNumber) {
		// ������õ�ҳ��ֵ<=0���򽫵�ǰҳ��ֵ����Ϊ1
		if (currentPageNumber <= 0) {
			this.currentPageNumber = 1;
			flush();
			return;
		}
		// ������õ�ҳ��ֵ���ܵ�ҳ��ֵ���򽫵�ǰҳ������Ϊ���ҳ��ֵ���ܵ�ҳ��ֵ��
		if (currentPageNumber >= totalPage) {
			if (totalPage > 0) {
				this.currentPageNumber = this.totalPage;
				flush();
				return;
			} else {
				this.currentPageNumber = 1;
			}
		}

		this.currentPageNumber = currentPageNumber;
		flush();
	}

}
