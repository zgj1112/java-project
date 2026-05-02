-- create database if not exists myproject;
DROP TABLE IF EXISTS dept;
CREATE TABLE dept
(
    id          BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT 'ID, 主键',
    name        VARCHAR(50) NOT NULL UNIQUE COMMENT '部门名称',
    count       INT          DEFAULT 0 COMMENT '部门人数',
    remark      VARCHAR(255) DEFAULT NULL COMMENT '备注',
    create_time DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) COMMENT '部门表';

-- ================== 通用树形节点表 ==================

-- 1. 树定义表（支持多棵树共存，如「菜单树」「分类树」「区域树」）
DROP TABLE IF EXISTS tree_node;
DROP TABLE IF EXISTS tree_def;

CREATE TABLE tree_def
(
    id          BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT 'ID，主键',
    code        VARCHAR(50)  NOT NULL UNIQUE COMMENT '树编码，如 MENU / CATEGORY / REGION',
    name        VARCHAR(100) NOT NULL COMMENT '树名称',
    remark      VARCHAR(255)          DEFAULT NULL COMMENT '备注',
    create_time DATETIME              DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) COMMENT '树定义表（一行代表一棵树）';


-- 2. 通用树形节点表（邻接表）
CREATE TABLE tree_node
(
    id          BIGINT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT 'ID，主键',
    tree_id     BIGINT UNSIGNED NOT NULL COMMENT '所属树 ID（关联 tree_def.id）',
    parent_id   BIGINT UNSIGNED          DEFAULT NULL COMMENT '父节点 ID，NULL 表示根节点',
    name        VARCHAR(100)    NOT NULL COMMENT '节点名称',
    code        VARCHAR(100)             DEFAULT NULL COMMENT '节点编码（业务标识，可选）',
    sort        INT             NOT NULL DEFAULT 0 COMMENT '同级排序序号，越小越靠前',
    level       TINYINT         NOT NULL DEFAULT 1 COMMENT '节点层级，根节点为 1',
    is_leaf     TINYINT(1)      NOT NULL DEFAULT 1 COMMENT '是否叶子节点：1 是 / 0 否',
    path        VARCHAR(500)             DEFAULT NULL COMMENT '祖先路径，如 /1/3/7/，便于快速查子树',
    ext_json    JSON                     DEFAULT NULL COMMENT '扩展字段（存业务自定义属性）',
    status      TINYINT(1)      NOT NULL DEFAULT 1 COMMENT '状态：1 启用 / 0 禁用',
    remark      VARCHAR(255)             DEFAULT NULL COMMENT '备注',
    create_time DATETIME                 DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME                 DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',

    CONSTRAINT fk_tree_node_tree FOREIGN KEY (tree_id) REFERENCES tree_def (id),
    CONSTRAINT fk_tree_node_parent FOREIGN KEY (parent_id) REFERENCES tree_node (id),
    INDEX idx_tree_parent (tree_id, parent_id),
    INDEX idx_path (path(191))
) COMMENT '通用树形节点表（邻接表）';